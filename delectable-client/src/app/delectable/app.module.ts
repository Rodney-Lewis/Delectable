import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { ScheduleService } from './schedule/schedule.service';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { PaginationComponent } from './_component/pagination/pagination.component';
import { CarddeckComponent } from './_component/carddeck/carddeck.component';
import { ListHeaderComponent } from './_component/list-header/list-header.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeService } from './recipe/recipe.service';
import { RecipeCardInfoComponent } from './recipe/templates/recipe-card-info/recipe-card-info.component';
import { AutoResizeTextfieldDirective } from './_directives/auto-resize-textfield/auto-resize-textfield.directive';
import { RestaurantCardInfoComponent } from './restaurant/templates/restaurant-card-info/restaurant-card-info.component';
import { AutoAddDirectionDirective } from './recipe/directives/auto-add-direction.directive';
import { AutoAddIngredientDirective } from './recipe/directives/auto-add-ingredient.directive';
import { AutoRemoveDirectionDirective } from './recipe/directives/auto-remove-direction.directive';
import { AutoRemoveIngredientDirective } from './recipe/directives/auto-remove-ingredient.directive';
import { ScheduleCalendarComponent } from './schedule/component/schedule-calendar/schedule-calendar.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { CalendarMultiDateSelectDirective } from './schedule/directives/calendar-multi-date-select.directive';
import { FixedButtonToContainerDirective } from './recipe/directives/fixed-button-to-container.directive';
import { LoginComponent } from './login/component/login-page/login.component';
import { AuthService } from './login/user_auth/auth.service';
import { TokenStorageService } from './login/user_auth/token-storage.service';
import { authInterceptorProviders } from './login/user_auth/auth.interceptor';
import { SignupComponent } from './login/component/signup/signup.component';
import { AdminConsoleComponent } from './admin/admin-console/admin-console.component';
import { AdminService } from './admin/admin-service.service';
import { ProfileComponent } from './admin/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeFormComponent,
    RecipeDetailComponent,
    ScheduleCalendarComponent,
    ScheduleFormComponent,
    RestaurantFormComponent,
    RestaurantListComponent,
    RestuarantDetailComponent,
    PaginationComponent,
    CarddeckComponent,
    RestaurantCardInfoComponent,
    ListHeaderComponent,
    RecipeCardInfoComponent,
    AutoResizeTextfieldDirective,
    AutoAddIngredientDirective,
    AutoAddDirectionDirective,
    AutoRemoveIngredientDirective,
    AutoRemoveDirectionDirective,
    ScheduleWeekAtAGlanceComponent,
    CalendarMultiDateSelectDirective,
    FixedButtonToContainerDirective,
    LoginComponent,
    SignupComponent,
    AdminConsoleComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
  ],
  providers: [RecipeService, ScheduleService, AuthService, TokenStorageService, AdminService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
