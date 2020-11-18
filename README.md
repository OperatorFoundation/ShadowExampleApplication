ShadowExampleApplication setup:

    1) Build and open up the app

    2) Press send.  The text below should change to "Finished"

    3) Check the run tab, if the program was successful, there should be a print and log message saying "Read Successful!"

ShapeshifterAndroidKotlin dependency setup:

    1) add the following at the end of repositories in your PROJECT's build.gradle:
	    allprojects {
	    	repositories {
	    		...
	    		maven { url 'https://jitpack.io' }
	    	}
	    }

	2) add the dependency in your MODULE's build.gradle:
		dependencies {
    	        implementation 'com.github.OperatorFoundation:ShapeshifterAndroidKotlin:0.2.1'
    	        implementation 'org.bouncycastle:bcpkix-jdk15on:1.58'
    	}