import { BrowserModule } from '@angular/platform-browser';
import { ChangeDetectorRef, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { ScheduleService } from './schedule/schedule.service';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { PaginationComponent } from './_component/pagination/pagination.component';
import { CarddeckComponent } from './_component/carddeck/carddeck.component';
import { ListToolbarComponent } from './_component/list-toolbar/list-toolbar.component';
import { ListHeaderComponent } from './_component/list-header/list-header.component';
import { RecipeListComponent } from './meal/recipe/component/recipe-list/recipe-list.component';
import { PantryDetailComponent } from './meal/pantry/component/pantry-detail/pantry-detail.component';
import { PantryFormComponent } from './meal/pantry/component/pantry-form/pantry-form.component';
import { PantryListComponent } from './meal/pantry/component/pantry-list/pantry-list.component';
import { PantryService } from './meal/pantry/pantry.service';
import { PantryCardInfoComponent } from './meal/pantry/templates/pantry-card-info/pantry-card-info.component';
import { RecipeDetailComponent } from './meal/recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './meal/recipe/component/recipe-form/recipe-form.component';
import { RecipeService } from './meal/recipe/recipe.service';
import { RecipeCardInfoComponent } from './meal/recipe/templates/recipe-card-info/recipe-card-info.component';
import { AutoAddDirectionDirective } from './meal/_directives/auto-add-direction.directive';
import { AutoAddIngredientDirective } from './meal/_directives/auto-add-ingredient.directive';
import { AutoRemoveDirectionDirective } from './meal/_directives/auto-remove-direction.directive';
import { AutoRemoveIngredientDirective } from './meal/_directives/auto-remove-ingredient.directive';
import { AutoResizeTextfieldDirective } from './_directives/auto-resize-textfield/auto-resize-textfield.directive';
import { RestaurantCardInfoComponent } from './restaurant/templates/restaurant-card-info/restaurant-card-info.component';

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
    AutoResizeTextfieldDirective,
    AutoAddIngredientDirective,
    AutoAddDirectionDirective,
    AutoRemoveIngredientDirective,
    AutoRemoveDirectionDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
  ],
  providers: [RecipeService, PantryService, ScheduleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
