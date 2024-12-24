import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router} from "@angular/router";
import {AssociationService} from "../../service/association.service";
import {Association} from "../../model/association";
import {FormControl, FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelect, MatSelectModule} from "@angular/material/select";
import {NgxMatSelectSearchModule} from "ngx-mat-select-search";
import {State} from "../../model/state";
import {ReplaySubject, Subject, takeUntil} from "rxjs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-edit-association',
  standalone: true,
  imports: [CommonModule, FormsModule, MatInputModule, MatSelectModule,
    ReactiveFormsModule, NgxMatSelectSearchModule, MatFormFieldModule],
  templateUrl: './edit-association.component.html',
  styleUrl: './edit-association.component.scss'
})
export class EditAssociationComponent implements OnInit, OnDestroy {

  @ViewChild("associationForm")
  associationForm!: NgForm;

  @ViewChild('stateSelect') stateSelect: MatSelect | undefined;

  editAssociationForm: Association = new Association();

  associationId: any;
  isSubmitted: boolean = false;
  header: string = '';

  states: State[] = [];

  stateFilterCtrl: FormControl<string | null> = new FormControl<string>('');
  filteredStates: ReplaySubject<State[]> = new ReplaySubject<State[]>();
  _onDestroy = new Subject<void>();

  constructor(private router: Router, private route: ActivatedRoute, private service: AssociationService, private toaster: ToastrService) {
  }

  ngOnInit(): void {
    this.loadStates();

    this.associationId = this.route.snapshot.params['associationId'];
    if (this.associationId) {
      this.getAssociationDetailById();
      this.header = 'Update Association'
    } else {
      this.header = 'Add Association'
    }

    this.stateFilterCtrl.valueChanges.pipe(takeUntil(this._onDestroy)).subscribe(() => {
      this.filterStates();
    });
  }

  loadStates() {
    this.service.findAllStates().subscribe({
      next: data => {
        if (data != null && data.body != null) {
          this.states = data.body
          this.filteredStates.next(this.states);
        }
      }
    })
  }

  getAssociationDetailById() {
    this.service.getAssociation(this.associationId).subscribe({
      next: data => {
        if (data != null && data.body != null) {
          const resultData = data.body;
          if (resultData) {
            this.editAssociationForm.id = resultData.id;
            this.editAssociationForm.name = resultData.name;
            this.editAssociationForm.email = resultData.email;
            this.editAssociationForm.phone = resultData.phone;
            this.editAssociationForm.address = resultData.address;
            this.editAssociationForm.zipCode = resultData.zipCode;
            this.editAssociationForm.state = resultData.state;
          }
        }
      }, error: () => {
      }
    });
  }

  protected filterStates() {
    if (!this.states) {
      return;
    }
    // get the search keyword
    let search = this.stateFilterCtrl.value;
    if (!search) {
      this.filteredStates.next(this.states.slice());
      return;
    } else {
      search = search.toLowerCase();
    }
    this.filteredStates.next(
      this.states.filter(state => search && state.name.toLowerCase().indexOf(search) > -1)
    );
  }

  ngOnDestroy(): void {
    this._onDestroy.next();
    this._onDestroy.complete();
  }

  save(isValid: boolean) {
    this.isSubmitted = true;
    if (isValid) {
      this.editAssociationForm.state = this.stateSelect?.value?.id || this.editAssociationForm.state
      this.service.saveAssociation(this.editAssociationForm).subscribe({
        next: data => {
          if (data && data.status == 204) {
            this.toaster.success('Successfully added Association - "' + this.editAssociationForm.name + '"');
            this.router.navigate(['dashboard']).then(r => console.log(r));
          } else
            this.toaster.error('Failed adding Association');
        },
        error: () => {
          this.toaster.error('Failed adding Association');
        }
      })
    }
  }
}
