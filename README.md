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
To set a custom layout to the operation failed view
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:gravity="center"
              android:orientation="vertical">

    <TextView
        android:id="@+id/label"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:fontFamily="sans-serif-light"
        android:text="Operation Failed"
        android:textAlignment="center"
        android:textSize="14sp"/>

    <TextView
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:background="@color/colorPrimaryDark"
        android:padding="10dp"
        android:text="TextView"
        android:textColor="@android:color/white"
        android:textSize="16sp"/>
</LinearLayout>
```
The custom layout should contain two views that inherit from TextView (or support the setText method) named "label" and "button". The onRefreshClicked click event is added to the view named "button".
