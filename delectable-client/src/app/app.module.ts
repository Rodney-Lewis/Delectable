import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'; 
import { AppComponent } from './app.component';
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
import { ScheduleListComponent } from './component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './component/schedule-form/schedule-form.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent,
    PantryFormComponent,
    NavComponent,
    PantryListComponent,
    RecipeForm2Component,
    ScheduleListComponent,
    ScheduleFormComponent
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
