Setup:

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