import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UserEditComponent } from './user-edit/user-edit.component';
import { UserLoginComponent} from './user-login/user-login.component';

const routes: Routes = [
  { path: 'users', component:UserListComponent, canActivate: [UserListComponent]},
  { path: 'users/:id/edit', component:UserEditComponent, canActivate: [UserEditComponent]},
  { path: 'users/new', component:UserEditComponent, canActivate: [UserEditComponent]},
  { path: 'login', component:UserLoginComponent},
  { path: '**', redirectTo:'login', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
