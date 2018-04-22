import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements AfterViewInit {

  @ViewChild('drawer') drawer: MatDrawer;

  ngAfterViewInit() {
    setTimeout(() => {
      this.drawer.open();
    }, 50);
  }
}
