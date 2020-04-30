import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IChapter } from 'app/shared/model/chapter.model';

type EntityResponseType = HttpResponse<IChapter>;
type EntityArrayResponseType = HttpResponse<IChapter[]>;

@Injectable({ providedIn: 'root' })
export class ChapterService {
  public resourceUrl = SERVER_API_URL + 'api/chapters';

  constructor(protected http: HttpClient) {}

  create(chapter: IChapter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chapter);
    return this.http
      .post<IChapter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(chapter: IChapter): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chapter);
    return this.http
      .put<IChapter>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IChapter>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChapter[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(chapter: IChapter): IChapter {
    const copy: IChapter = Object.assign({}, chapter, {
      dateAdded: chapter.dateAdded && chapter.dateAdded.isValid() ? chapter.dateAdded.format(DATE_FORMAT) : undefined,
      dateModified: chapter.dateModified && chapter.dateModified.isValid() ? chapter.dateModified.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateAdded = res.body.dateAdded ? moment(res.body.dateAdded) : undefined;
      res.body.dateModified = res.body.dateModified ? moment(res.body.dateModified) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((chapter: IChapter) => {
        chapter.dateAdded = chapter.dateAdded ? moment(chapter.dateAdded) : undefined;
        chapter.dateModified = chapter.dateModified ? moment(chapter.dateModified) : undefined;
      });
    }
    return res;
  }
}
