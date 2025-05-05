import { Component, ViewEncapsulation } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-head',
  imports: [RouterModule],
  templateUrl:'./head.component.html',
  styleUrl: './head.component.css',
  encapsulation:ViewEncapsulation.None,
})
export class HeadComponent {

}
