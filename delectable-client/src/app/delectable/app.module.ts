import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { ScheduleFormComponent } from './schedule/component/schedule-form/schedule-form.component';
import { RestaurantFormComponent } from './restaurant/component/restaurant-form/restaurant-form.component';
import { RestaurantListComponent } from './restaurant/component/restaurant-list/restaurant-list.component';
import { RestuarantDetailComponent } from './restaurant/component/restaurant-detail/restaurant-detail.component';
import { PaginationComponent } from './shared/component/pagination/pagination.component';
import { CarddeckComponent } from './shared/component/carddeck/carddeck.component';
import { ListHeaderComponent } from './shared/component/list-header/list-header.component';
import { RecipeListComponent } from './recipe/component/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/component/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/component/recipe-form/recipe-form.component';
import { RecipeCardInfoComponent } from './recipe/component/recipe-card-info/recipe-card-info.component';
import { AutoResizeTextfieldDirective } from './shared/directives/auto-resize-textfield/auto-resize-textfield.directive';
import { RestaurantCardInfoComponent } from './restaurant/component/restaurant-card-info/restaurant-card-info.component';
import { AutoAddDirectionDirective } from './recipe/directives/auto-add-direction.directive';
import { AutoAddIngredientDirective } from './recipe/directives/auto-add-ingredient.directive';
import { AutoRemoveDirectionDirective } from './recipe/directives/auto-remove-direction.directive';
import { AutoRemoveIngredientDirective } from './recipe/directives/auto-remove-ingredient.directive';
import { ScheduleCalendarComponent } from './schedule/component/schedule-calendar/schedule-calendar.component';
import { ScheduleWeekAtAGlanceComponent } from './schedule/component/schedule-week-at-a-glance/schedule-week-at-a-glance.component';
import { CalendarMultiDateSelectDirective } from './schedule/directives/calendar-multi-date-select.directive';
import { FixedButtonToContainerDirective } from './recipe/directives/fixed-button-to-container.directive';
import { LoginComponent } from './user/component/login-page/login.component';
import { authInterceptorProviders } from './user/auth.interceptor';
import { SignupComponent } from './user/component/signup/signup.component';
import { AdminConsoleComponent } from './user/component/admin-console/admin-console.component';
import { ProfileComponent } from './user/component/profile/profile.component';
import { AccountComponent } from './user/component/account/profile.component';
import { ChangePasswordComponent } from './user/component/change-password/change-password.component';
import { UserSettingsComponent } from './user/component/user-settings/user-settings.component';
import { UserService } from './user/service/user.service';
import { RecipeService } from './recipe/service/recipe.service';
import { AuthService } from './user/service/auth.service';
import { TokenStorageService } from './user/service/token-storage.service';
import { AdminService } from './user/service/admin-service.service';
import { ScheduleService } from './schedule/service/schedule.service';
import { ComboCardInfoComponent } from './combo/component/combo-card-info/combo-card-info.component';
import { ComboDetailComponent } from './combo/component/combo-detail/combo-detail.component';
import { ComboFormComponent } from './combo/component/combo-form/combo-form.component';
import { ComboListComponent } from './combo/component/combo-list/combo-list.component';

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
    AccountComponent,
    ChangePasswordComponent,
    UserSettingsComponent,
    ComboCardInfoComponent,
    ComboDetailComponent,
    ComboFormComponent,
    ComboListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
  ],
  providers: [RecipeService, ScheduleService, AuthService, TokenStorageService, AdminService, UserService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
