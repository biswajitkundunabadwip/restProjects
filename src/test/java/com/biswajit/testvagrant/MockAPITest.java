package com.biswajit.testvagrant;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.biswajit.mock.payloads.MockPayLoads;
import com.biswajit.testbase.TestBase;

import io.restassured.path.json.JsonPath;

public class MockAPITest extends TestBase {

	static int foreginPlayerCounter;
	static int wicketKeeperCounter;
	int maxForeginPlayerAllowed = 4;
	int maxAllwoedWickerKeeper = 1;
	JsonPath newJson ;
	
	@BeforeClass(alwaysRun = true)
	public void init() {
		 newJson = jsonPathExtracTor(MockPayLoads.rcbTeamResponse());
	}

	@Test (groups = "foregin-player-count-validations")
	public void maxForegienPlayerValidations() {
		int playerSizeinJson = newJson.get("player.size()");
		for (int i = 0; i < playerSizeinJson; i++) {
			String extractedCountryName = newJson.get("player[" + i + "].country").toString();
			if (!extractedCountryName.equalsIgnoreCase("India")) {
				foreginPlayerCounter++;
			}
		}
		Assert.assertEquals(foreginPlayerCounter, maxForeginPlayerAllowed);
	}
	
	@Test(groups = "wicketkeeper-count-validations")
	public void wickerKeperValidations() {
		int playerSizeInJson = newJson.get("player.size()");
		for (int i = 0; i < playerSizeInJson; i++) {
			String extractedRoleName = newJson.get("player[" + i + "].role").toString();
			if (extractedRoleName.equalsIgnoreCase("Wicket-keeper")) {
				wicketKeeperCounter++;
			}
		}
		Assert.assertEquals(wicketKeeperCounter, maxAllwoedWickerKeeper);
	}

}
