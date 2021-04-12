# CryptoBell

# Install
### dependencies
* docker
* maven
* spring
* Node JS 12

#### Node JS
Install Node.js 12.19.0 (https://nodejs.org/en/download/releases/) then run:
```console
$ npm rebuild node-sass
```



# Usage

##Production
Inside the "react" folder run:
```console
$ npm run build
```

Then run:
```console
$ docker-compose build 
```
After a success bompilation run:
```console
$ docker-compose up
```
The app should be acessible [here](http://localhost:8080)

##Development

During the front-end development you can do a easy deploy with node and use the spring to serve de API, instead of building the app all the time
```console
$ docker-compose up
```
Then go to the react folder and start a [server](http://localhost:3000) to serve only the frontend
```console
$ npm start
```


