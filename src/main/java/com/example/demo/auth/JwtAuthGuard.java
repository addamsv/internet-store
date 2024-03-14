package com.example.demo.auth;// import {
//   CanActivate,
//   ExecutionContext,
//   Injectable,
//   UnauthorizedException,
// } from '@nestjs/common';
// import { JwtService } from '@nestjs/jwt';
// import { Observable } from 'rxjs';

public class JwtAuthGuard {
  public JwtAuthGuard() {}

//  canActivate(
//    context: ExecutionContext,
//  ): boolean | Promise<boolean> | Observable<boolean> {
//    const req = context.switchToHttp().getRequest();
//
//    try {
//      const authHeader = req.headers.authorization;
//
//      const [bearer, token] = authHeader.split(' ');
//
//      if (bearer !== 'Bearer' || !token) {
//        throw new UnauthorizedException({
//          message: 'Пользователь неавторизован',
//        });
//      }
//
//      const user = this.jwtService.verify(token);
//
//      req.user = user;
//
//      return true;
//    } catch (e) {
//      // console.log(e);
//
//      throw new UnauthorizedException({
//        message: 'Пользователь неавторизован',
//      });
//    }
//  }
}
