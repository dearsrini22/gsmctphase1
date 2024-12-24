import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AssociationService} from "../../service/association.service";

@Component({
  selector: 'app-view-association',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './view-association.component.html',
  styleUrl: './view-association.component.scss'
})
export class ViewAssociationComponent implements OnInit {
  associationId: any;
  associationDetail: any = [];

  constructor(private route: ActivatedRoute, private service: AssociationService) {
  }

  ngOnInit(): void {
    this.associationId = this.route.snapshot.params['associationId'];
    this.getAssociationDetailById();
  }

  getAssociationDetailById() {
    this.service.getAssociation(this.associationId).subscribe({
      next: data => {
        if (data != null && data.body != null) {
          const resultData = data.body;
          if (resultData) {
            this.associationDetail = resultData;
          }
        }
      }, error: err => {
      }
    });
  }
}
