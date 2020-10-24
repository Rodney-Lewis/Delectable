import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { PantryFormComponent } from './pantry/component/pantry-form/pantry-form.component';
import { PantryService } from './pantry/service/pantry.service';
import { PantryListComponent } from './pantry/component/pantry-list/pantry-list.component';
import { CommonModule } from '@angular/common';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { ScheduleService } from './schedule/schedule.service';
import { PantryDetailComponent } from './pantry/component/pantry-detail/pantry-detail.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeService } from './recipe/service/recipe.service';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { PaginationComponent } from './_component/pagination/pagination.component';
import { CarddeckComponent } from './_component/carddeck/carddeck.component';
import { ListToolbarComponent } from './_component/list-toolbar/list-toolbar.component';
import { RestaurantCardInfoComponent } from './restaurant/_component/restaurant-card-info/restaurant-card-info.component';
import { ListHeaderComponent } from './_component/list-header/list-header.component';
import { PantryCardInfoComponent } from './pantry/_component/pantry-card-info/pantry-card-info.component';
import { RecipeCardInfoComponent } from './recipe/_templates/recipe-card-info/recipe-card-info.component';
import { PantryDirective } from './recipe/component/pantry.directive';
import { AutoAddInput } from './recipe/_templates/auto-add/autoAddInput';
import { AutoRemoveInput } from './recipe/_templates/auto-remove/autoRemoveInput';
import { AutoResizeTextfieldDirective } from './recipe/_templates/auto-resize-textfield/auto-resize-textfield.directive'

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent,
    PantryFormComponent,
    PantryListComponent,
    ScheduleListComponent,
    ScheduleFormComponent,
    PantryDetailComponent,
    RestaurantFormComponent,
    RestaurantListComponent,
    RestuarantDetailComponent,
    PaginationComponent,
    CarddeckComponent,
    ListToolbarComponent,
    RestaurantCardInfoComponent,
    ListHeaderComponent,
    PantryCardInfoComponent,
    RecipeCardInfoComponent,
    PantryDirective,
    AutoAddInput,
    AutoRemoveInput,
    AutoResizeTextfieldDirective
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
