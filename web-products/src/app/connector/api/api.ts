export * from './categoryController.service';
import { CategoryControllerService } from './categoryController.service';
export * from './productController.service';
import { ProductControllerService } from './productController.service';
export const APIS = [CategoryControllerService, ProductControllerService];
