import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserEditComponent } from './user-edit/user-edit.component';

import { UserApiRestService } from './shared/user-api-rest.service';
import { DataService } from './shared/data.service';

import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserLoginComponent } from './user-login/user-login.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserEditComponent,
    UserLoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    UserApiRestService,
    DataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
