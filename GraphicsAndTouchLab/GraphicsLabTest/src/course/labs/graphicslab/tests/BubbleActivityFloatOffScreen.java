package course.labs.graphicslab.tests;

import course.labs.graphicslab.BubbleActivity;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class BubbleActivityFloatOffScreen extends ActivityInstrumentationTestCase2<BubbleActivity> {
  	private Solo solo;
  	
  	public BubbleActivityFloatOffScreen() {
		super(BubbleActivity.class);
  	}

  	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
  	}
  
	public void testRun() {
		// Wait for activity: 'course.labs.TouchLab.BubbleActivity'
		solo.waitForActivity(course.labs.graphicslab.BubbleActivity.class, 2000);

		// Click on action bar item
		solo.clickOnMenuItem("Single Speed Mode");
		
		// Click to create a bubble
		solo.clickOnScreen(500, 500);
		
		solo.sleep(1000);

		// Assert that a bubble was displayed 
		assertEquals("Bubble hasn't appeared", 1, solo.getCurrentViews(course.labs.graphicslab.BubbleActivity.BubbleView.class).size());

		solo.sleep(2000);
		
		// Assert that the bubble has left the screen
		assertEquals("Bubble hasn't left the screen", 0, solo.getCurrentViews(course.labs.graphicslab.BubbleActivity.BubbleView.class).size());

	}
}
