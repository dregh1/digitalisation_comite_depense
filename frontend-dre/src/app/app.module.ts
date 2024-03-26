import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonnelComponent } from './components/personnel/personnel/personnel.component';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { PersonnelService } from './services/personnel.service';
import { FormsModule } from '@angular/forms';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { Observable } from 'rxjs';
import { AccueilComponent } from './accueil/accueil.component';

// import { KeycloakService } from '@keycloak/keycloak-js';

// function corsInterceptor(): HttpInterceptor {
//   return {
//     intercept(req: HttpRequest<any>, handler: HttpHandler): Observable<HttpEvent<any>> {
//       const modifiedReq = req.clone({
//         setHeaders: {
//           'Access-Control-Allow-Origin': 'http://localhost:8081', // Remplacer par l'origine autorisée si nécessaire
//         },
//       });
//       return handler.handle(modifiedReq);
//     },
//   };
// }
@NgModule({
  declarations: [
    AppComponent,
    PersonnelComponent,
    LoginComponent,
    HomeComponent,
    AccueilComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule ,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    PersonnelService,
    // { provide: HTTP_INTERCEPTORS, useFactory: corsInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
