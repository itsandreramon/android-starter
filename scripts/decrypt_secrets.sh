#!/bin/bash

decrypt() {
  PASSPHRASE=$1
  INPUT=$2
  OUTPUT=$3
  gpg --quiet --batch --yes --decrypt --passphrase="$PASSPHRASE" --output $OUTPUT $INPUT
}

if [[ ! -z "$ENCRYPT_KEY" ]]; then
  decrypt ${ENCRYPT_KEY} release/debug.keystore.gpg release/debug.keystore.jks
else
  echo "ENCRYPT_KEY is empty"
fi