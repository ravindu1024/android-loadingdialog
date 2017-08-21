# Android Loading Views
 - A non blocking customisable progress view that can can block click events without blocking the back button
 - A better looking loading dialog for Android inspired by ios
 
 Installation:
 
 Add this to the main gradle file: 
 ```gradle
 	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add this to your project gradle file:
```gradle
	dependencies {
	        compile 'com.github.ravindu1024:android-loadingdialog:1.0.2'
	}

```

# Usage
To create a new loading view
```java
LoadingView loadingView = new LoadingView.Builder(act)
                .setProgressColorResource(R.color.ew_dark_pink)
                .setBackgroundColorRes(R.color.ew_white)
                .setProgressStyle(LoadingView.ProgressStyle.HORIZONTAL)
                .setCustomMargins(0, 100, 100, 0)
                .attachTo(act);

loadingView.show();
```
To set the failed state with a retry calback
```java
loadingView.setLoadingFailed("Operation failed", "retry", new LoadingView.OnRefreshClickListener()
                {
                    @Override
                    public void onRefreshClicked()
                    {
		    	//do stuff here
                    }
                });
```
