import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PantryFormComponent } from './pantry/component/pantry-form/pantry-form.component';
import { PantryListComponent } from './pantry/component/pantry-list/pantry-list.component';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { PantryDetailComponent } from './pantry/component/pantry-detail/pantry-detail.component';
import { RecipeListComponent } from './meal/recipe/component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './meal/recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './meal/recipe/component/recipe-form/recipe-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { PreparedFoodListComponent } from './meal/preparedfood/component/prepared-food-list/prepared-food-list.component';
import { PreparedFoodFormComponent } from './meal/preparedfood/component/prepared-food-form/prepared-food-form.component';
import { PreparedFoodDetailComponent } from './meal/preparedfood/component/prepared-food-detail/prepared-food-detail.component';

const routes: Routes = [
  { path: '', redirectTo:new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch:'full'},
  { path: ':epoch', component: ScheduleListComponent},
  { path: 'schedule/add', component: ScheduleFormComponent},
  { path: 'recipe/list', component: RecipeListComponent },
  { path: 'pantry/list', component: PantryListComponent },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'pantry/detail/:id', component: PantryDetailComponent },
  { path: 'recipe/add', component: RecipeFormComponent },
  { path: 'recipe/edit/:id', component: RecipeFormComponent },
  { path: 'pantry/add', component: PantryFormComponent },
  { path: 'restaurant/list', component: RestaurantListComponent },
  { path: 'restaurant/add', component: RestaurantFormComponent },
  { path: 'restaurant/edit/:id', component: RestaurantFormComponent },
  { path: 'restaurant/detail/:id', component: RestuarantDetailComponent },
  { path: 'prepared/list', component: PreparedFoodListComponent },
  { path: 'prepared/add', component: PreparedFoodFormComponent },
  { path: 'prepared/edit/:id', component: PreparedFoodFormComponent },
  { path: 'prepared/detail/:id', component: PreparedFoodDetailComponent }

];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }