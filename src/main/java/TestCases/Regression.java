package TestCases;

/**********IMPORTS***************/

import java.io.IOException;

import org.testng.annotations.Test;

import com.FindingHospitals.BaseHome;

//************************* Regression page class for testing********************//

public class Regression extends BaseHome {

	@Test(groups = "Regression Suite")
	public void login() throws Exception {

		clickLogin();
	}

	@Test(groups = "Regression Suite", priority = 1)
	public void hospital() throws Exception {

		clickBangalore();

	}

	@Test(groups = "Regression Suite", priority = 2)
	public void searchhospitals() throws Exception {

		clickHospital();
	}

	@Test(groups = "Regression Suite", priority = 3)
	public void hospitalnames() throws Exception {

		getHospitalList();

	}

	@Test(groups = "Regression Suite", priority = 4)
	public void hospitaldata() throws Exception {

		hospitalsdata();
	}

	@Test(groups = "Regression Suite", priority = 5)
	public void diagnostics() throws Exception {

		Diagnosticspage();

	}

	@Test(groups = "Regression Suite", priority = 6)
	public void diagnostic() throws Exception {

		TopCities();

	}

	@Test(groups = "Regression Suite", priority = 7)
	public void diagnosticdata() throws Exception {
		topcities();

	}

	@Test(groups = "Regression Suite", priority = 8)
	public void corporatepage() throws IOException, InterruptedException {

		CopWellness();

	}

}
