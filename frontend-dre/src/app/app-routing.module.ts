import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonnelComponent } from './components/personnel/personnel/personnel.component';
import { LoginComponent } from './login/login.component';
import { AccueilComponent } from './accueil/accueil.component';
const routes: Routes = [
  { path: 'personnel', component: PersonnelComponent },
  {path:'login',component:LoginComponent},
  {path:'accueil',component:AccueilComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
