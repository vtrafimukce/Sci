package org.viktar.sci;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;

Here's the corrected implementation of the `multiply` method in the `Cluster` class: 

public Cluster multiply(int count) {
    if (count < 0) {
        throw new IllegalArgumentException("Count must be non-negative");
    }
    double newMass = this.mass * count;
    int newProtons = this.protons * count;
    int newNeutrons = this.neutrons * count;
    String newName = "(" + this.toString() + ")*" + count;
    return new Cluster(newMass, newProtons, newNeutrons, newName);
}
