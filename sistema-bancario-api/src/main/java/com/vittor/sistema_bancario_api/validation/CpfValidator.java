package com.vittor.sistema_bancario_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {

        int primeiroDigitoCalculado = calcularPrimeiroDigito(cpf);

        int primeiroDigitoInformado =
                Character.getNumericValue(cpf.charAt(9));

        return primeiroDigitoCalculado == primeiroDigitoInformado;
    }

    private int calcularPrimeiroDigito(String cpf) {

        int soma = 0;

        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }
    private int calcularSegundoDigito(String cpf) {

        int soma = 0;

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }

        int resto = soma % 11;

        if (resto < 2) {
            return 0;
        }

        return 11 - resto;
    }


}