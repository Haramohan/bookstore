import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBook, Book } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { ISubject } from 'app/shared/model/subject.model';
import { SubjectService } from 'app/entities/subject/subject.service';

@Component({
  selector: 'jhi-book-update',
  templateUrl: './book-update.component.html'
})
export class BookUpdateComponent implements OnInit {
  isSaving = false;
  subjects: ISubject[] = [];
  dateAddedDp: any;
  dateModifiedDp: any;

  editForm = this.fb.group({
    id: [],
    booktitle: [null, [Validators.required]],
    text: [null, [Validators.required]],
    isbn: [null, [Validators.required]],
    publisher: [null, [Validators.required]],
    language: [null, [Validators.required]],
    pubyear: [null, [Validators.required]],
    pages: [null, [Validators.required]],
    price: [null, [Validators.required]],
    author: [null, [Validators.required]],
    status: [null, [Validators.required]],
    dateAdded: [],
    dateModified: [],
    subjectName: [null, Validators.required]
  });

  constructor(
    protected bookService: BookService,
    protected subjectService: SubjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ book }) => {
      this.updateForm(book);

      this.subjectService.query().subscribe((res: HttpResponse<ISubject[]>) => (this.subjects = res.body || []));
    });
  }

  updateForm(book: IBook): void {
    this.editForm.patchValue({
      id: book.id,
      booktitle: book.booktitle,
      text: book.text,
      isbn: book.isbn,
      publisher: book.publisher,
      language: book.language,
      pubyear: book.pubyear,
      pages: book.pages,
      price: book.price,
      author: book.author,
      status: book.status,
      dateAdded: book.dateAdded,
      dateModified: book.dateModified,
      subjectName: book.subjectName
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const book = this.createFromForm();
    if (book.id !== undefined) {
      this.subscribeToSaveResponse(this.bookService.update(book));
    } else {
      this.subscribeToSaveResponse(this.bookService.create(book));
    }
  }

  private createFromForm(): IBook {
    return {
      ...new Book(),
      id: this.editForm.get(['id'])!.value,
      booktitle: this.editForm.get(['booktitle'])!.value,
      text: this.editForm.get(['text'])!.value,
      isbn: this.editForm.get(['isbn'])!.value,
      publisher: this.editForm.get(['publisher'])!.value,
      language: this.editForm.get(['language'])!.value,
      pubyear: this.editForm.get(['pubyear'])!.value,
      pages: this.editForm.get(['pages'])!.value,
      price: this.editForm.get(['price'])!.value,
      author: this.editForm.get(['author'])!.value,
      status: this.editForm.get(['status'])!.value,
      dateAdded: this.editForm.get(['dateAdded'])!.value,
      dateModified: this.editForm.get(['dateModified'])!.value,
      subjectName: this.editForm.get(['subjectName'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBook>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ISubject): any {
    return item.id;
  }
}
