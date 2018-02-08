import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'removespace',
    pure: false
})
export class RemoveSpaceAndSpecialCharsFilter implements PipeTransform {
/**
 * Remove spaces and char '&'
 * @param text
 */
    transform(text: String): any {
        return text.toLowerCase().replace(/[\s]/g, '').replace('&', '_');
    }
}
