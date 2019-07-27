import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 
import { AppComponent } from './app.component';

import { Recipe } from './model/recipe';
import { RecipeService } from './service/recipe.service';
import { RecipeFormComponent } from './component/recipe-form/recipe-form.component';
import { RecipeDetailComponent } from './component/recipe-detail/recipe-detail.component';
import { RecipeListComponent } from './component/recipe-list/recipe-list.component';


@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [RecipeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
