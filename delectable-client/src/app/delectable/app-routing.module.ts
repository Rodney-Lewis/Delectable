import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { YourGuardGuard } from './login/path_gaurd/your-guard.guard';
import { LoginComponent } from './login/component/login-page/login.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { AdminConsoleComponent } from './admin/admin-console/admin-console.component';
import { SignupComponent } from './login/component/signup/signup.component';
import { ProfileComponent } from './admin/profile/profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminConsoleComponent },
  { path: 'user/edit/:id', component: ProfileComponent },
  { path: 'register', component: SignupComponent },
  { path: '', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' },
  { path: ':epoch', component: ScheduleWeekAtAGlanceComponent },
  { path: 'schedule/add', component: ScheduleFormComponent, canActivate: [YourGuardGuard] },
  { path: 'recipe/add', component: RecipeFormComponent, canActivate: [YourGuardGuard] },
  { path: 'recipe/edit/:id', component: RecipeFormComponent, canActivate: [YourGuardGuard] },
  { path: 'recipe/detail/:id', component: RecipeDetailComponent },
  { path: 'recipe/list', component: RecipeListComponent },
  { path: 'pantry/edit/:id', component: RecipeFormComponent, canActivate: [YourGuardGuard] },
  { path: 'restaurant/list', component: RestaurantListComponent },
  { path: 'restaurant/add', component: RestaurantFormComponent, canActivate: [YourGuardGuard] },
  { path: 'restaurant/edit/:id', component: RestaurantFormComponent, canActivate: [YourGuardGuard] },
  { path: 'restaurant/detail/:id', component: RestuarantDetailComponent },
  { path: '**', redirectTo: new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate()).getTime().toString(), pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }