# PackageUpdaterAPI
 
The PackageUpdaterAPI is a simple way to load packages/modules from your webspace in your java program. The Open-Source-Project is licensed under the MIT license.

### Installation
To install the API you have to go to the [release-site](https://github.com/IamPekka058/PackageUpdaterAPI/releases/latest/) and download the jar file under "Assets". For the next step you have to include the API in your project.

#### Structure of a package module
If you want to see an example package/module go to the "Example" folder. The url of the ExamplePackage would look like "https://www.example.com/ExamplePackage/latest/ExamplePackage.zip". You will have to create a json file called "info.json" with following parameters: 
```json
{
    "version":"test_version"
}
```
### Examples
```java
//Creating a instance of the PackageUpdater
PackageUpdater packageupdater = new PackageUpdater(path, url);

//To get all installed packages/modules just call getAllLocalPackages()
ArrayList<Package> packages = packageupdater.getAllLocalPackages();

//To install a package/module call getPackage()
Package installed_package = packageupdater.getPackage(name);
```

