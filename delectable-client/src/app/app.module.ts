import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 
import { AppComponent } from './app.component';
import { Recipe } from './model/recipe';
import { RecipeService } from './service/recipe.service';
import { RecipeFormComponent } from './component/recipe-form/recipe-form.component';
import { RecipeDetailComponent } from './component/recipe-detail/recipe-detail.component';
import { RecipeListComponent } from './component/recipe-list/recipe-list.component';
import { PantryFormComponent } from './component/pantry-form/pantry-form.component';
import { PantryService } from './service/pantry.service';
import { NavComponent } from './component/nav/nav.component';
import { PantryListComponent } from './component/pantry-list/pantry-list.component';
import { RecipeForm2Component } from './component/recipe-form2/recipe-form2.component';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent,
    PantryFormComponent,
    NavComponent,
    PantryListComponent,
    RecipeForm2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  providers: [RecipeService, PantryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
