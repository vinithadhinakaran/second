package TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.FindingHospitals.BaseHome;

//********************* Smoke testing *********************//

public class Smoke extends BaseHome {

	@Test(groups = "Smoke Suite")
	public void login() throws Exception {

		clickLogin();
	}

	@Test(groups = "Smoke Suite", priority = 1)
	public void hospital() throws Exception {
		clickBangalore();

	}

	@Test(groups = "Smoke Suite", priority = 2)
	public void searchhospitals() throws Exception {

		clickHospital();
	}

	@Test(groups = "Smoke Suite", priority = 3)
	public void hospitalnames() throws Exception {

		getHospitalList();

	}

	@Test(groups = "Smoke Suite", priority = 4)
	public void hospitaldata() throws Exception {

		hospitalsdata();
	}

	@Test(groups = "Smoke Suite", priority = 5)
	public void diagnostics() throws Exception {

		Diagnosticspage();

	}

	@Test(groups = "Smoke Suite", priority = 6)
	public void diagnostic() throws Exception {

		TopCities();

	}

	@Test(groups = "Smoke Suite", priority = 7)
	public void diagnosticdata() throws Exception {
		topcities();

	}

	@Test(groups = "Smoke Suite", priority = 8)
	public void corporatepage() throws IOException, InterruptedException {

		CopWellness();

	}

}
