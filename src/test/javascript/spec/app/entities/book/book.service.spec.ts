import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BookService } from 'app/entities/book/book.service';
import { IBook, Book } from 'app/shared/model/book.model';
import { Lang } from 'app/shared/model/enumerations/lang.model';
import { StatusFlag } from 'app/shared/model/enumerations/status-flag.model';

describe('Service Tests', () => {
  describe('Book Service', () => {
    let injector: TestBed;
    let service: BookService;
    let httpMock: HttpTestingController;
    let elemDefault: IBook;
    let expectedResult: IBook | IBook[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BookService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Book(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        Lang.English,
        0,
        0,
        0,
        'AAAAAAA',
        StatusFlag.Active,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateAdded: currentDate.format(DATE_FORMAT),
            dateModified: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Book', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAdded: currentDate.format(DATE_FORMAT),
            dateModified: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateModified: currentDate
          },
          returnedFromService
        );

        service.create(new Book()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Book', () => {
        const returnedFromService = Object.assign(
          {
            booktitle: 'BBBBBB',
            text: 'BBBBBB',
            isbn: 'BBBBBB',
            publisher: 'BBBBBB',
            language: 'BBBBBB',
            pubyear: 1,
            pages: 1,
            price: 1,
            author: 'BBBBBB',
            status: 'BBBBBB',
            dateAdded: currentDate.format(DATE_FORMAT),
            dateModified: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateModified: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Book', () => {
        const returnedFromService = Object.assign(
          {
            booktitle: 'BBBBBB',
            text: 'BBBBBB',
            isbn: 'BBBBBB',
            publisher: 'BBBBBB',
            language: 'BBBBBB',
            pubyear: 1,
            pages: 1,
            price: 1,
            author: 'BBBBBB',
            status: 'BBBBBB',
            dateAdded: currentDate.format(DATE_FORMAT),
            dateModified: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAdded: currentDate,
            dateModified: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Book', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
