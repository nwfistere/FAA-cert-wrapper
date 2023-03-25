package com.nickfistere.airmendatabase.airmencertification;

import com.nickfistere.airmendatabase.airmencertification.NonPilotBasic.NonPilotBasicQueryModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.NonPilotCert.NonPilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.PilotBasic.NonPilotBasicService;
import com.nickfistere.airmendatabase.airmencertification.PilotBasic.PilotBasicQueryModel;
import com.nickfistere.airmendatabase.airmencertification.PilotBasic.PilotBasicService;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertModel;
import com.nickfistere.airmendatabase.airmencertification.PilotCert.PilotCertRepositoryInterface;
import com.nickfistere.airmendatabase.airmencertification.importDb.*;
import com.nickfistere.airmendatabase.airmencertification.util.ApplicationProperties;
import org.instancio.Instancio;
import org.instancio.Model;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.instancio.Select.field;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AirmenCertificationApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AirmenCertificationApplicationTests {

	@Autowired
	ImportService importService;

	@Autowired
	NonPilotBasicService nonPilotBasicService;

	@Autowired
	PilotBasicService pilotBasicService;

	@Spy
	ImportRequest importRequest = new ImportRequest();

	static ExampleMatcher matcher = ExampleMatcher.matching()
			.withIgnoreCase()
			.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

	@BeforeAll
	static void setup(
			@Autowired NonPilotBasicRepositoryInterface nonPilotBasicRepositoryInterface,
			@Autowired NonPilotCertRepositoryInterface nonPilotCertRepositoryInterface,
			@Autowired PilotBasicRepositoryInterface pilotBasicRepositoryInterface,
			@Autowired PilotCertRepositoryInterface pilotCertRepositoryInterface
	) {
		// Setup database
		List<NonPilotBasicModel> nonPilotModels = Instancio.ofList(NonPilotBasicModel.class).size(10).create();
		List<PilotBasicModel> pilotModels = Instancio.ofList(PilotBasicModel.class).size(10).create();

		// Certs have foreign keys to the basic models, need to reference them.
		Model<NonPilotCertModel> nonPilotCertModel = Instancio.of(NonPilotCertModel.class)
				.supply(field(NonPilotCertModel::getUniqueId),
						(random) -> nonPilotModels.stream().map(BasicModel::getUniqueId).toList().get(random.intRange(0, 9))
				).toModel();

		Model<PilotCertModel> pilotCertModel = Instancio.of(PilotCertModel.class)
				.supply(field(PilotCertModel::getUniqueId),
						(random) -> pilotModels.stream().map(BasicModel::getUniqueId).toList().get(random.intRange(0, 9))
				).toModel();

		nonPilotBasicRepositoryInterface.saveAll(nonPilotModels);
		nonPilotCertRepositoryInterface.saveAll(Instancio.ofList(nonPilotCertModel).size(10).create());
		pilotBasicRepositoryInterface.saveAll(pilotModels);
		pilotCertRepositoryInterface.saveAll(Instancio.ofList(pilotCertModel).size(10).create());
	}

	@AfterAll
	static void tearDown(@Autowired ApplicationProperties applicationProperties) {
		List<String> filesToCleanup = List.of(
				"NONPILOT_BASIC.csv",
				"NONPILOT_CERT.csv",
				"PILOT_BASIC.csv",
				"PILOT_CERT.csv"
		);
		for(String file : filesToCleanup) {
			File f = new File(applicationProperties.getDestination() + file);
			if (f.exists()) {
				try {
					boolean success = f.delete();
					if (!success) {
						throw new RuntimeException();
					}
				} catch (Exception e) {
					Assert.fail("Failed to cleanup files after test.");
				}
			}
		}
	}

	@Test
	void testImportService_Valid() throws Exception {

		File file = ResourceUtils.getFile("classpath:mock.zip");
		URL mockUrl = file.toURI().toURL();

		doReturn(mockUrl).when(importRequest).getHref();

		Future<List<ImportResult>> imports = importService.importDb(importRequest);

		List<ImportResult> importResults = imports.get(30, TimeUnit.SECONDS);

		Assert.assertEquals(4, importResults.size());

		for (ImportResult result: importResults) {
			Assert.assertEquals(result.getPass(), true);
			Assert.assertNull(result.getException());
		}

	}

	@Test
	void testImportService_InvalidUrl() throws Exception {

		doThrow(new MalformedURLException()).when(importRequest).getHref();

		Future<List<ImportResult>> imports = importService.importDb(importRequest);

		List<ImportResult> importResults = imports.get(30, TimeUnit.SECONDS);

		Assert.assertEquals(1, importResults.size());

		for (ImportResult result: importResults) {
			Assert.assertEquals(result.getPass(), false);
			Assert.assertNotNull(result.getException());
		}

	}

	@Test
	void testFindNonPilotsByExample_FindAll() {
		Pageable pageable = PageRequest.of(0, 10);

		NonPilotBasicQueryModel model = new NonPilotBasicQueryModel();

		Example<NonPilotBasicQueryModel> example = Example.of(model, matcher);
		Page<NonPilotBasicQueryModel> models = nonPilotBasicService.findNonPilotsByExample(example, pageable);

		Assert.assertNotNull(models);
		Assert.assertEquals(models.getSize(), 10);
	}

	@Test
	void testFindPilotsByExample_FindAll() {
		Pageable pageable = PageRequest.of(0, 10);

		PilotBasicQueryModel model = new PilotBasicQueryModel();

		Example<PilotBasicQueryModel> example = Example.of(model, matcher);
		Page<PilotBasicQueryModel> models = pilotBasicService.findPilotsByExample(example, pageable);

		Assert.assertNotNull(models);
		Assert.assertEquals(models.getSize(), 10);
	}

}
