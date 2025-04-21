import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientShowComponent } from './client-show.component';

describe('ClientShowComponent', () => {
  let component: ClientShowComponent;
  let fixture: ComponentFixture<ClientShowComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientShowComponent]
    });
    fixture = TestBed.createComponent(ClientShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
