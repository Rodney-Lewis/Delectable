import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PantryFormComponent } from './pantry/component/pantry-form/pantry-form.component';
import { PantryListComponent } from './pantry/component/pantry-list/pantry-list.component';
import { ScheduleListComponent } from './schedule/component/schedule-list/schedule-list.component';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { PantryDetailComponent } from './pantry/component/pantry-detail/pantry-detail.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';

const routes: Routes = [
  { path: '', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' },
  { path: ':epoch', component: ScheduleListComponent },
  { path: 'schedule/add', component: ScheduleFormComponent },
  { path: 'recipe/add', component: RecipeFormComponent },
  { path: 'recipe/edit/:id', component: RecipeFormComponent },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'recipe/list', component: RecipeListComponent },
  { path: 'pantry/detail/:id', component: PantryDetailComponent },
  { path: 'pantry/list', component: PantryListComponent },
  { path: 'pantry/add', component: PantryFormComponent },
  { path: 'pantry/edit/:id', component: RecipeFormComponent },
  { path: 'restaurant/list', component: RestaurantListComponent },
  { path: 'restaurant/add', component: RestaurantFormComponent },
  { path: 'restaurant/edit/:id', component: RestaurantFormComponent },
  { path: 'restaurant/detail/:id', component: RestuarantDetailComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }