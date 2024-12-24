import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormControl, FormsModule, NgForm, ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatOptionModule} from "@angular/material/core";
import {MatSelect, MatSelectModule} from "@angular/material/select";
import {NgxMatSelectSearchModule} from "ngx-mat-select-search";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {HomeOwner} from "../../model/home-owner";
import {State} from "../../model/state";
import {ReplaySubject, Subject, takeUntil} from "rxjs";
import {AssociationService} from "../../service/association.service";
import {Association} from "../../model/association";
import {AssociationSearch} from "../../model/association-search";

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [CommonModule, FormsModule, MatFormFieldModule, MatOptionModule, MatSelectModule, NgxMatSelectSearchModule, ReactiveFormsModule],
    templateUrl: './register.component.html',
    styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit, OnDestroy {
    constructor(public modal: NgbActiveModal, private service: AssociationService) {
    }

    @ViewChild("homeOwnerForm")
    homeOwnerForm!: NgForm;

    @ViewChild('stateSelect') stateSelect: MatSelect | undefined;
    @ViewChild('associationSelect') associationSelect: MatSelect | undefined;

    states: State[] = [];
    stateFilterCtrl: FormControl<string | null> = new FormControl<string>('');
    filteredStates: ReplaySubject<State[]> = new ReplaySubject<State[]>();

    associationFilterCtrl: FormControl<string | null> = new FormControl<string>('');
    filteredAssociations: ReplaySubject<Association[]> = new ReplaySubject<Association[]>();

    _onDestroy = new Subject<void>();

    editHomeOwnerForm: HomeOwner = new HomeOwner();
    isSubmitted: boolean = false;

    ngOnInit(): void {
        this.loadStates();
        this.stateFilterCtrl.valueChanges.pipe(takeUntil(this._onDestroy)).subscribe(() => {
            this.filterStates();
        });
        this.associationFilterCtrl.valueChanges.pipe(takeUntil(this._onDestroy)).subscribe(() => {
            this.filterAssociations()
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

    filterStates() {
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

    filterAssociations() {
        let search = this.associationFilterCtrl.value;
        const state: string = this.stateSelect && this.stateSelect.value && this.stateSelect.value.id;
        if (search && search.length > 2) {
            this.service.findAllAssociations(new AssociationSearch(search, state)).subscribe({
                next: data => {
                    if (data != null && data.body != null) {
                        this.filteredAssociations.next(data.body);
                    }
                }
            })
        }
    }

  register(homeOwner: HomeOwner) {
    homeOwner.state = this.stateSelect && this.stateSelect.value && this.stateSelect.value.id
    homeOwner.associationId = this.associationSelect && this.associationSelect.value && this.associationSelect.value.id
    this.service.register(homeOwner).subscribe({
      next: data => {
        if (data != null && data.status == 204) {
          this.modal.close("Registered. You will be contacted further")
        }
      }
    })
  }

    ngOnDestroy(): void {
        this._onDestroy.next();
        this._onDestroy.complete();
    }

    noResults(): string {
      if(this.associationFilterCtrl && this.associationFilterCtrl.value) {
        const pendingChars: number = (3 - this.associationFilterCtrl.value.length)
        return pendingChars < 1 ? 'No results found' : 'Please enter ' + pendingChars +  ' or more characters'
      } else {
        return 'No results found'
      }
    }
}
