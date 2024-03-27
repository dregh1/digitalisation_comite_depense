import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LogComponent } from './log/log.component';
import { EditComponent } from './edit/edit.component';
import { PrescripteurComponent } from './prescripteur/prescripteur.component';
const routes: Routes = [
  {path:'',component:LogComponent},
  {path:'home',component:HomeComponent},
  {path:'edit',component:EditComponent},
  {path:'prescripteur',component:PrescripteurComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
