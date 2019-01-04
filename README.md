# SWAPI-sample-android
Simple Android app which downloads data from SWAPI and saves it to the file. This app's purpose is to display how the network requests and file storage can be done in Android.

### Description
Application has two screens. The first one contains the button that triggers the transaction to the second screen. The second screen checks the availability of the saved file. If the file exists, the info about films is retrieved from the file, otherwise the films info is received from the server.

[Retrofit](https://square.github.io/retrofit/) and [GSON](https://github.com/google/gson) are used for simplier network communication handling.

### API
The data to be downloaded and displayed is retrieved from the [here](https://swapi.co/api/films/).
