import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditarVinoComponent } from './editar-vino/editar-vino.component';
import { VinoListarComponent } from './vino-listar/vino-listar.component';


const routes: Routes = [
{path: 'vinos', component:VinoListarComponent},
{path: 'vinos/:id/editar', component:EditarVinoComponent},
{path: 'vinos/nuevo', component:EditarVinoComponent},
{path: '*', redirectTo: 'vinos',pathMatch:'full'},
{path: '', redirectTo: 'vinos',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
