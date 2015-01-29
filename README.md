#List View Sample

##Introduction
This application is a tech demo that shows how to display a list of items that contain data pulled from a server using HTTP GET. It includes Android UI testing using [Espresso](https://code.google.com/p/android-test-kit/wiki/Espresso).

###To Do
- Get unit testing to work better. Unit testing using [Robolectric](http://robolectric.org/) is still a work in progress. Robolectric allows us to run unit tests on the local JVM (fast) instead of using the Android emulator or physical device (slow).  Right now can run unit tests using Robolectric via gradle on the command line, but not directly from Android Studio or IDEA.

##Walkthrough
###Setup Dependencies
The application needs a data source. A simple NodeJS/Express app (data_source) is included to provide that data source. 

1. Install [NodeJS](http://nodejs.org/) if necessary.
2. In a terminal window, navigate to the data_source folder. 
3. Type `npm install` to install Express. 
4. Type `node app.js` to start the server.

###Application Walkthrough
1. Import the `/android` project into your Android development IDE of choice.
2. Open `/app/src/main/res/values/strings.xml` and change the `data_server_ip` value to your machine's IP address. *Note*, this should not be set to `localhost` or `127.0.0.1`, as those will refer to the Android device environment at runtime.
3. Run the app. (It was tested on a Nexus 5 API 21 emulator during development.)
4. Clicking the Simple button shows a simple ListView of strings using an ArrayAdapter.
5. Clicking the Complex button shows a ListView of more complex items using a custom BaseAdapter subclass. The list of names can be filtered by typing into the field above the list.