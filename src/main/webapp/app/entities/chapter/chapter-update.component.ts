import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IChapter, Chapter } from 'app/shared/model/chapter.model';
import { ChapterService } from './chapter.service';
import { IBook } from 'app/shared/model/book.model';
import { BookService } from 'app/entities/book/book.service';

@Component({
  selector: 'jhi-chapter-update',
  templateUrl: './chapter-update.component.html'
})
export class ChapterUpdateComponent implements OnInit {
  isSaving = false;
  books: IBook[] = [];
  dateAddedDp: any;
  dateModifiedDp: any;

  editForm = this.fb.group({
    id: [],
    chapterTitle: [null, [Validators.required]],
    chapterNumber: [null, [Validators.required]],
    pages: [null, [Validators.required]],
    type: [null, [Validators.required]],
    order: [null, [Validators.required]],
    status: [null, [Validators.required]],
    dateAdded: [],
    dateModified: [],
    bookISBN: [null, Validators.required]
  });

  constructor(
    protected chapterService: ChapterService,
    protected bookService: BookService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chapter }) => {
      this.updateForm(chapter);

      this.bookService.query().subscribe((res: HttpResponse<IBook[]>) => (this.books = res.body || []));
    });
  }

  updateForm(chapter: IChapter): void {
    this.editForm.patchValue({
      id: chapter.id,
      chapterTitle: chapter.chapterTitle,
      chapterNumber: chapter.chapterNumber,
      pages: chapter.pages,
      type: chapter.type,
      order: chapter.order,
      status: chapter.status,
      dateAdded: chapter.dateAdded,
      dateModified: chapter.dateModified,
      bookISBN: chapter.bookISBN
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chapter = this.createFromForm();
    if (chapter.id !== undefined) {
      this.subscribeToSaveResponse(this.chapterService.update(chapter));
    } else {
      this.subscribeToSaveResponse(this.chapterService.create(chapter));
    }
  }

  private createFromForm(): IChapter {
    return {
      ...new Chapter(),
      id: this.editForm.get(['id'])!.value,
      chapterTitle: this.editForm.get(['chapterTitle'])!.value,
      chapterNumber: this.editForm.get(['chapterNumber'])!.value,
      pages: this.editForm.get(['pages'])!.value,
      type: this.editForm.get(['type'])!.value,
      order: this.editForm.get(['order'])!.value,
      status: this.editForm.get(['status'])!.value,
      dateAdded: this.editForm.get(['dateAdded'])!.value,
      dateModified: this.editForm.get(['dateModified'])!.value,
      bookISBN: this.editForm.get(['bookISBN'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChapter>>): void {
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

  trackById(index: number, item: IBook): any {
    return item.id;
  }
}
