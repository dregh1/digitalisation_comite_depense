import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PersonnelService } from './services/personnel.service';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { LogComponent } from './log/log.component';
import { EditComponent } from './edit/edit.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { HeaderComponent } from './header/header.component';
import { PrescripteurComponent } from './prescripteur/prescripteur.component';
import { FilterByTitlePipe } from './models/FilterByTitle';

// function initializeKeyCloak(keycloak: KeycloakService)
// {
//   return ()=>
//   keycloak.init({
//     config:{
//       url:'http://localhost:8081/auth',
//       realm :'oma',
//       clientId:'angular-client',
//     },
//     initOptions:{
//       onLoad:'login-required',
//       flow:"standard"
//     }
//   })
// }


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LogComponent,
    EditComponent,
    HeaderComponent,
    PrescripteurComponent,
    FilterByTitlePipe,
  ],
  imports: [
    BrowserModule,
    HttpClientModule ,
    AppRoutingModule,
    FormsModule,
    KeycloakAngularModule,
   
  ],
  providers: [
    PersonnelService,
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory : initializeKeyCloak,
    //   multi: true,
    //   deps:[KeycloakService],

    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
