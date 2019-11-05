[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RoundableLayout-green.svg?style=true)](https://android-arsenal.com/details/1/7934)
[![](https://jitpack.io/v/zladnrms/RoundableLayout.svg)](https://jitpack.io/#zladnrms/RoundableLayout)

_RoundableLayout_ is an easy to make round corner layout in an Android. And it implements ConstraintLayout, so you can also handle inner layout directly.

## preview

<img src="./preview_image.jpg" width="200px" /><img src="./preview_hagaren.png" width="200px" />

## What's New in _RoundableLayout_ 1.0.2?

- Add Background Color Option.

		
## Installation

* **Gradle**

Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

Add the dependency in your app build.gradle
```gradle
dependencies {
		implementation 'androidx.constraintlayout:constraintlayout:1.1.3 or high' // maybe already exists or add
		implementation 'com.github.zladnrms:RoundableLayout:1.0.2'
	}
```

    
## How to use

#### in .xml

```xml
   <com.tistory.zladnrms.roundablelayout.RoundableLayout
    android:id="@+id/layout_example"
    android:layout_width="300dp"
    android:layout_height="300dp"
    android:elevation="5dp" // native shadow options
    android:translationZ="5dp" // native shadow options
    app:backgroundColor="#FFFFFF" // if TRANSPARENT, shadow do not apply.
    app:cornerLeftTop="40dp"
    app:cornerRightTop="40dp"
    app:cornerLeftBottom="40dp"
    app:cornerRightBottom="40dp">
```
    
            
###### cornerLeftTop : Layout's left top round value. (default = 0dp)
###### cornerRightTop : Layout's right top round value. (default = 0dp)
###### cornerLeftBottom : Layout's left bottom round value. (default = 0dp)
###### cornerRightBottom : Layout's right bottom round value. (default = 0dp)
###### backgroundColor : Layout's background color value. (default = Color.WHITE)

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
