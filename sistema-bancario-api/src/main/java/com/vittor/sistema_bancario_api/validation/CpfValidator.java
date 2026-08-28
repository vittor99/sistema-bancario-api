package com.vittor.sistema_bancario_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {

        // Se o CPF estiver vazio ou não tiver exatamente 11 números,
        // essa validação matemática não é executada.
        // @NotBlank e @Pattern são responsáveis por esses erros.
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return true;
        }
        // Rejeita CPFs formados pelo mesmo número repetido.
        // chars() transforma os caracteres do CPF em um fluxo.
        // distinct() remove os caracteres repetidos.
        // count() conta quantos caracteres diferentes sobraram.
        // Se sobrar apenas 1, significa que todos os dígitos são iguais.
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

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