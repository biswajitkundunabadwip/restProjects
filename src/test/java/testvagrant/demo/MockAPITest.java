package testvagrant.demo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import mock.payloads.MockPayLoads;
import testBase.TestBase;

public class MockAPITest extends TestBase {

	static int foreginPlayerCounter;
	static int wicketKeeperCounter;
	int maxForeginPlayerAllowed = 4;
	int maxAllwoedWickerKeeper = 1;
	JsonPath newJson ;
	
	@BeforeClass
	public void init() {
		 newJson = jsonPathExtracTor(MockPayLoads.rcbTeamResponse());
	}

	@Test
	public void maxForegienPlayerValidations() {
		int playerSizeinJson = newJson.get("player.size()");
		for (int i = 0; i < playerSizeinJson; i++) {
			System.out.println("Current country name is " + newJson.get("player[" + i + "].country").toString());
			String extractedCountryName = newJson.get("player[" + i + "].country").toString();
			if (!extractedCountryName.equalsIgnoreCase("India")) {
				foreginPlayerCounter++;
			}
		}
		Assert.assertEquals(foreginPlayerCounter, maxForeginPlayerAllowed);
	}
	
	@Test
	public void wickerKeperValidations() {
		int playerSizeInJson = newJson.get("player.size()");
		for (int i = 0; i < playerSizeInJson; i++) {
			System.out.println("Current player name is " + newJson.get("player[" + i + "].name").toString());
			String extractedRoleName = newJson.get("player[" + i + "].role").toString();
			if (extractedRoleName.equalsIgnoreCase("Wicket-keeper")) {
				wicketKeeperCounter++;
			}
		}
		Assert.assertEquals(wicketKeeperCounter, maxAllwoedWickerKeeper);
	}

}
