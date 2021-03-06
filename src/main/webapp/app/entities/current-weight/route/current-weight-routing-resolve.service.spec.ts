jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ICurrentWeight, CurrentWeight } from '../current-weight.model';
import { CurrentWeightService } from '../service/current-weight.service';

import { CurrentWeightRoutingResolveService } from './current-weight-routing-resolve.service';

describe('Service Tests', () => {
  describe('CurrentWeight routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: CurrentWeightRoutingResolveService;
    let service: CurrentWeightService;
    let resultCurrentWeight: ICurrentWeight | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(CurrentWeightRoutingResolveService);
      service = TestBed.inject(CurrentWeightService);
      resultCurrentWeight = undefined;
    });

    describe('resolve', () => {
      it('should return ICurrentWeight returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultCurrentWeight = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultCurrentWeight).toEqual({ id: 123 });
      });

      it('should return new ICurrentWeight if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultCurrentWeight = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultCurrentWeight).toEqual(new CurrentWeight());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultCurrentWeight = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultCurrentWeight).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
