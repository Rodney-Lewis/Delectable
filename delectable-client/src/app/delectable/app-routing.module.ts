import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { YourGuardGuard } from './user/path_gaurd/your-guard.guard';
import { LoginComponent } from './user/component/login-page/login.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { AdminConsoleComponent } from './user/component/admin-console/admin-console.component';
import { SignupComponent } from './user/component/signup/signup.component';
import { ProfileComponent } from './user/component/profile/profile.component';
import { AccountComponent } from './user/component/account/profile.component';
import { UserSettingsComponent } from './user/component/user-settings/user-settings.component';
import { ChangePasswordComponent } from './user/component/change-password/change-password.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'settings', component: UserSettingsComponent, children: [
      { path: 'profile', component: AccountComponent },
      { path: 'security', component: ChangePasswordComponent },
      { path: '', redirectTo: 'profile', pathMatch: 'full' }
    ]
  },
  { path: 'admin', component: AdminConsoleComponent },
  { path: 'user/edit/:id', component: ProfileComponent },
  { path: 'account/:id', component: AccountComponent },
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