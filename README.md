[![](https://jitpack.io/v/zladnrms/RoundableLayout.svg)](https://jitpack.io/#zladnrms/RoundableLayout)

_RoundableLayout_ is an easy to make layout corner round in an Android.
and it implements ConstraintLayout, so can directly handle inner layout)

## What's New in _Roudnable_ 1.0.0?

- Publish Version

## How to Setup

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
    
	dependencies {
	        implementation 'androidx.constraintlayout:constraintlayout:1.1.3' // maybe already exists or add
	        implementation 'com.github.zladnrms:RoundableLayout:1.0.0'
	}
    
## Customize Way

_RoundableLayout_'s exacy way to round the edges, two conditions must be met.

#### in .xml
    <com.tistory.zladnrms.roundablelayout.RoundableLayout
            android:id="@+id/layout_example"
            android:layout_width="300dp"
            android:layout_height="300dp"
            android:clipChildren="false"
            android:clipToPadding="false"
            android:background="@drawable/background_roundablelayout"
            android:elevation="6dp"
            android:translationZ="2dp"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:cornerLeftTop="40dp"
            app:cornerRightTop="40dp"
            app:cornerLeftBottom="40dp"
            app:cornerRightBottom="40dp">
            
###### cornerLeftTop : Layout's left top round value. (If not noted, 0dp)
###### cornerRightTop : Layout's right top round value. (If not noted, 0dp)
###### cornerLeftBottom : Layout's left bottom round value. (If not noted, 0dp)
###### cornerRightBottom : Layout's right bottom round value. (If not noted, 0dp)

##### @drawable/background_roundablelayout

    <?xml version="1.0" encoding="utf-8"?>
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
        <solid android:color="#ffffff"/>
        <corners android:topLeftRadius="40dp"
             android:topRightRadius="40dp"
             android:bottomLeftRadius="40dp"
             android:bottomRightRadius="40dp"/>
    </shape>
    
##### they are native options in Android.

# License

    Copyright 2019 Wookun Kim

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
