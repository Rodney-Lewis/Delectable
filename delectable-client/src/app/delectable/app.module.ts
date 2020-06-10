import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PantryFormComponent } from './pantry/component/pantry-form/pantry-form.component';
import { PantryService } from './pantry/pantry.service';
import { NavComponent } from './nav/component/header/nav.component';
import { PantryListComponent } from './pantry/component/pantry-list/pantry-list.component';
import { CommonModule } from '@angular/common';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { ScheduleService } from './schedule/schedule.service';
import { PantryDetailComponent } from './pantry/component/pantry-detail/pantry-detail.component';
import { RecipeListComponent } from './meal/recipe/component/recipe-list/recipe-list.component';
import { RecipeFormComponent } from './meal/recipe/component/recipe-form/recipe-form.component';
import { RecipeDetailComponent } from './meal/recipe/component/recipe-detail/recipe-detail.component';
import { RecipeService } from './meal/recipe/recipe.service';

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent,
    PantryFormComponent,
    NavComponent,
    PantryListComponent,
    ScheduleListComponent,
    ScheduleFormComponent,
    PantryDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule
  ],
  providers: [RecipeService, PantryService, ScheduleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
