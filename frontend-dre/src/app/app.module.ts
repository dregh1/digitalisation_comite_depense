import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonnelComponent } from './components/personnel/personnel/personnel.component';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { PersonnelService } from './services/personnel.service';
import { FormsModule } from '@angular/forms';
<<<<<<< Updated upstream

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
=======
import { HomeComponent } from './component/creation_session/home.component';
import { LogComponent } from './component/log/log.component';
import { MenuComponent } from './component/menu/menu.component';
import { HeaderComponent } from './component/header/header.component';
import { EditComponent } from './component/prescripteur/prescripteur.component';
import { AuthenticationComponent } from './component/authentication/authentication.component';
import { TesteComponent } from './teste/teste.component';
import { ValidationComponent } from './component/validation/validation.component';
import { ConsultationComponent } from './component/consultation/consultation.component';
import { CreationPrescriComponent } from './component/creation-prescri/creation-prescri.component';
import { AffichagePrescriComponent } from './component/affichage-prescri/affichage-prescri.component';
import { NotificationComponent } from './component/notification/notification.component';
import { MainComponent } from './component/main/main.component';

>>>>>>> Stashed changes
@NgModule({
  declarations: [
    AppComponent,
    PersonnelComponent,
    LoginComponent,
    HomeComponent,
<<<<<<< Updated upstream
    AccueilComponent
=======
    LogComponent,
    EditComponent,
    MenuComponent,
    HeaderComponent,
    AuthenticationComponent,
    TesteComponent,
    ValidationComponent,
    ConsultationComponent,
    CreationPrescriComponent,
    AffichagePrescriComponent,
    NotificationComponent,
    MainComponent
>>>>>>> Stashed changes
  ],
  imports: [
    BrowserModule,
    HttpClientModule ,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    PersonnelService,
<<<<<<< Updated upstream
    // { provide: HTTP_INTERCEPTORS, useFactory: corsInterceptor, multi: true },
=======
>>>>>>> Stashed changes
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
