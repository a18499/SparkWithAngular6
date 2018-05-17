# SparkWithAngular6

This is a rough example of Spark + Angular 6 website.

### Manually Build Steps
1. `cd src/main/resources/html`
2. `npm install` (install all dependencies)
3. `ng build --prod`
4. IntelliJ IDEA -> Run
5. Open your favorite browser and type 127.0.0.1:4567 to view the site

### Create a clean project
1. use IntelliJ IDEA to create a clean Gradle project(Kotlin(Java))
2. add the following depedencies to your build.gradle
   ``` gradle
   compile "com.sparkjava:spark-kotlin:1.0.0-alpha"
   compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.7.25'
   ```
3. create directory `src/main/kotlin/{package_name}` and `src/main/{resources_name}`
4. `cd src/main/{resources_name}`
5. `ng new {angular_project_name}`

   prerequisite:
      1. Node.js
      2. JDK 8
      3. `npm i -g npm`
      4. `npm i -g @angular/cli`

   options:
      1. `npm i -g npm-check-updates` (easily update angular dependencies)
      2. `ncu -u`
      3. `npm install`
      #### notice: angular 6.0.2 require typescript@2.7.2, if you accidentally upgrade typescript to 2.8 or higher,
      ####         please downgrade to 2.7.2 with command `npm i typescript@2.7.2`.

6. Mark `src/main/{resources_name}` as `Resources Root` (IntelliJ IDEA -> Project Structure -> Modules)

### Code example
* Example: JSON

1. add a public API `/hello` at server-side

   src/main/kotlin/app/Main.kt:
   ``` kotlin
   package app


   import spark.kotlin.*

   fun main(args: Array<String>) {
       staticFiles.location("/html/dist/html")

       get("/hello") {
           type(contentType = "application/json")
           "{\"message\":\"Hello World\"}"
       }
   }
   ```
   
2. send a get request when angular initialized

   src/main/resources/html/src/app/app.component.ts:
   ``` typescript
   import { Component, OnInit } from '@angular/core';
   import { HttpClient } from '@angular/common/http';

   @Component({
     selector: 'app-root',
     templateUrl: './app.component.html',
     styleUrls: ['./app.component.css']
   })
   export class AppComponent implements OnInit {
     title = 'app';
     hello = '';

     constructor(private http: HttpClient) { }

     ngOnInit() {
       this.http.get('/hello').subscribe(
         res => this.hello = JSON.parse(JSON.stringify(res)).message);
     }
   }
   ```

3. add angular binding to see the received json content
   
   src/main/resources/html/src/app/app.component.html:
   ``` html
   <div style="text-align:center">
     <h1>
       Welcome to {{ title }}!
     </h1>
     <img width="300" alt="Angular Logo" src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNTAgMjUwIj4KICAgIDxwYXRoIGZpbGw9IiNERDAwMzEiIGQ9Ik0xMjUgMzBMMzEuOSA2My4ybDE0LjIgMTIzLjFMMTI1IDIzMGw3OC45LTQzLjcgMTQuMi0xMjMuMXoiIC8+CiAgICA8cGF0aCBmaWxsPSIjQzMwMDJGIiBkPSJNMTI1IDMwdjIyLjItLjFWMjMwbDc4LjktNDMuNyAxNC4yLTEyMy4xTDEyNSAzMHoiIC8+CiAgICA8cGF0aCAgZmlsbD0iI0ZGRkZGRiIgZD0iTTEyNSA1Mi4xTDY2LjggMTgyLjZoMjEuN2wxMS43LTI5LjJoNDkuNGwxMS43IDI5LjJIMTgzTDEyNSA1Mi4xem0xNyA4My4zaC0zNGwxNy00MC45IDE3IDQwLjl6IiAvPgogIDwvc3ZnPg==">
   </div>
   <h2>This is an example of Spark + Angular 6 project</h2>

   <p>{{hello}}</p>
   ```
